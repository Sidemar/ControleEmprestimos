package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controle.GerenciadorClientes;
import dao.DaoClienteMemoria;
import dominio.Cliente;
import excecao.ClienteInvalidoException;
import excecao.DataException;
import instanciahotel.ClienteHotel;


public class TestGerenciadorClientes {
	
	private GerenciadorClientes gerenciador = new GerenciadorClientes();
	private Cliente clienteValido;
	private Cliente clienteInvalido;
	
	@Before
	public void setUp() {
		clienteValido = createClienteHotel(1L, "Roberto", "12345678900", "123123", "Rua importante", 1, 1, 1998);
		clienteInvalido = createClienteHotel(2L, "", "", "", "Rua", new Date());
	}
	
	private Cliente createClienteHotel(long codigo, String nome, String cpf, String rg, String endereco, int dia, int mes, int ano) {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(Calendar.DAY_OF_MONTH, dia);
		dataNascimento.set(Calendar.MONTH, mes-1);
		dataNascimento.set(Calendar.YEAR, ano);
		return createClienteHotel(codigo, nome, cpf, rg, endereco, dataNascimento.getTime());
	}
	
	private Cliente createClienteHotel(long codigo, String nome, String cpf, String rg, String endereco, Date nascimento) {
		return new ClienteHotel(codigo, nome, cpf, rg, endereco, nascimento);
	}
	
	@After 
	public void tearDown(){
		//Como é singleton precisa limpar os dados a cada teste
		DaoClienteMemoria.getInstance().clear();
	}
	
	@Test(expected=ClienteInvalidoException.class)
	public void testPreconditionInvalid() throws DataException, ClienteInvalidoException {
		gerenciador.cadastrarCliente(clienteInvalido);
	}
	
	/** TESTES CADASTRAR */
	
	@Test
	public void testCadastrarClienteNormalBehavior() throws DataException, ClienteInvalidoException {				
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Cliente deve ser válido", clienteValido.valido());
		assertFalse("Cliente não deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		gerenciador.cadastrarCliente(clienteValido); // Se alguma pós condição não for obedecida o teste falhará
			
		assertTrue("Cliente não deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
	}
	
	@Test(expected=DataException.class)
	public void testCadastrarClienteRepetidoExceptionalBehavior() throws DataException, ClienteInvalidoException{		
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Cliente deve ser válido", clienteValido.valido());
		assertFalse("Cliente não deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		gerenciador.cadastrarCliente(clienteValido);		
		assertTrue("Cliente deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		gerenciador.cadastrarCliente(clienteValido);
	}
	
	/** TESTES REMOVER */
	
	@Test
	public void testRemoverClienteNormalBehavior() throws DataException, ClienteInvalidoException {				
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Cliente deve ser válido", clienteValido.valido());
		
		gerenciador.cadastrarCliente(clienteValido);
		assertTrue("Cliente deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		gerenciador.removerCliente(clienteValido);
		assertFalse("Cliente não deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
	}
	
	/* É melhor a gente criar testes parametrizados para as situações de entrada (id == 0, nao cadastrado, invalido, etc)? */
	@Test(expected=DataException.class)
	public void testRemoverClienteInexistenteExceptionalBehavior() throws DataException {				
		Cliente clienteNaoCadastrado = clienteValido;
		
		assertTrue("Cliente não deve ser nulo", clienteNaoCadastrado != null);
		assertTrue("Cliente deve ser válido", clienteNaoCadastrado.valido());
		assertFalse("Cliente não deve estar cadastrado", gerenciador.exists(clienteNaoCadastrado.getCodigo()));
		
		gerenciador.removerCliente(clienteNaoCadastrado);
	}
	
	@Test(expected=DataException.class)
	public void testRemoverClienteIdInvalidoExceptionalBehavior() throws DataException {				
		//Id <= 0 verifica uma condição especial da verificação e do exists()
		Cliente clienteNaoCadastrado = createClienteHotel(0L, "Roberto", "12345678900", "123123", "Rua importante", 1, 1, 1998);
		
		assertTrue("Cliente não deve ser nulo", clienteNaoCadastrado != null);
		assertFalse("Cliente não deve estar cadastrado", gerenciador.exists(clienteNaoCadastrado.getCodigo()));
		
		gerenciador.removerCliente(clienteNaoCadastrado);
	}
	
	/** TESTES UPDATE */

	@Test
	public void testAtualizarClienteNormalBehavior() throws DataException, ClienteInvalidoException{		
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Cliente deve ser válido", clienteValido.valido());
		
		gerenciador.cadastrarCliente(clienteValido);
		assertTrue("Cliente deve existir", gerenciador.exists(clienteValido.getCodigo()));
		
		clienteValido.setNome("Maria");
		gerenciador.updateCliente(clienteValido);
	}
	
	@Test(expected=ClienteInvalidoException.class)
	public void testAtualizarClienteInvalidoExceptionalBehavior() throws DataException, ClienteInvalidoException{		
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Cliente deve ser inicialmente válido", clienteValido.valido());
		gerenciador.cadastrarCliente(clienteValido);
		assertTrue("Cliente deve existir", gerenciador.exists(clienteValido.getCodigo()));
		
		Cliente clienteInvalidado = clienteValido;
		clienteInvalidado.setNome("");
		
		assertTrue("Cliente deve ser inválido", !clienteInvalidado.valido());

		gerenciador.updateCliente(clienteInvalidado);
	}
	
	@Test(expected=DataException.class)
	public void testAtualizarClienteNaoCadastradoExceptionalBehavior() throws DataException, ClienteInvalidoException{		
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Cliente deve ser inicialmente válido", clienteValido.valido());
		assertTrue("Cliente não deve existir", !gerenciador.exists(clienteValido.getCodigo()));
		gerenciador.updateCliente(clienteValido);
	}
	
	/** TESTES GET */
	
	@Test
	public void testGetClienteNormalBehavior() throws DataException, ClienteInvalidoException {						
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Id do cliente deve ser maior que 0", clienteValido.getCodigo() > 0);
		
		gerenciador.cadastrarCliente(clienteValido);
		assertTrue("Cliente deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		Cliente cliente = gerenciador.getCliente(clienteValido.getCodigo());
		assertFalse("Cliente não deve ser nulo", cliente == null);
		assertTrue("Cliente deve ter Id igual ao consultado", cliente.getCodigo() == clienteValido.getCodigo());
	}
	
	@Test(expected=DataException.class)
	public void testGetClienteInexistenteExceptionalBehavior() throws DataException {						
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Id do cliente deve ser maior que 0", clienteValido.getCodigo() > 0);
		assertFalse("Cliente não deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		gerenciador.getCliente(clienteValido.getCodigo());
	}
	
	@Test(expected=DataException.class)
	public void testGetClienteIdInvalidoExceptionalBehavior() throws DataException {
		//Id <= 0 verifica uma condição especial da verificação e do exists()
		Cliente clienteIdInvalido = createClienteHotel(0L, "Lucas", "12345678900", "123123", "Rua sem nome", 27, 8, 1990);
		
		assertTrue("Cliente não deve ser nulo", clienteIdInvalido != null);
		assertFalse("Cliente não deve estar cadastrado", gerenciador.exists(clienteIdInvalido.getCodigo()));
		
		gerenciador.getCliente(clienteIdInvalido.getCodigo());
	}
	
	/** TESTES LISTAR */
	
	public void testListarClientesListaVaziaNormalBehavior() throws DataException {		
		List<Cliente> clientes = gerenciador.listarClientes();
		assertFalse("Lista não deve ser nula", clientes == null);
		assertTrue("Lista deve estar vazia", clientes.isEmpty());
	}
	
	public void testListarClientesNormalBehavior() throws DataException, ClienteInvalidoException {		
		assertTrue("Cliente não deve ser nulo", clienteValido != null);
		assertTrue("Cliente deve ser válido", clienteValido.valido());
		assertFalse("Cliente não deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		gerenciador.cadastrarCliente(clienteValido);		
		assertTrue("Cliente deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		List<Cliente> clientes = gerenciador.listarClientes();
		assertFalse("Lista não deve ser nula", clientes == null);
		assertFalse("Lista não deve ser vazia", clientes.isEmpty());
		assertTrue("Lista deve ter 1 item", clientes.size() == 1);
		assertTrue("Lista deve ter o cliente inserido", clientes.get(0).getCodigo() == clienteValido.getCodigo());
	}
	
	/** TESTES EXISTS */
	
	public void testExistsIdsInvalidosNormalBehavior() {		
		assertFalse(gerenciador.exists(-5));
		assertFalse(gerenciador.exists(0));
		assertFalse(gerenciador.exists(2));
	}
	
	public void testExistsIdsValidosNormalBehavior() throws DataException, ClienteInvalidoException {		
		gerenciador.cadastrarCliente(clienteValido);		
		assertTrue("Cliente deve estar cadastrado", gerenciador.exists(clienteValido.getCodigo()));
		
		assertTrue(gerenciador.exists(clienteValido.getCodigo()));
	}

}
