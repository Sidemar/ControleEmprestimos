package dao;

import java.util.List;

import dominio.Dominio;
import excecao.DataException;

public interface Dao {
	
	//@ public nullable model instance List list;
	
	/*@ 
	 @ public normal_behavior
	 @ 		requires obj != null;
	 @		requires !list.contains(obj);
	 @ 		assignable list;
	 @ 		ensures list.size() == \old(list.size()) + 1;
	 @ 		ensures list.get(list.size()-1) == obj;
	 @	also
	 @	public exceptional_behavior
	 @ 		requires obj != null;
	 @		requires list.contains(obj);
	 @		assignable \nothing;
	 @		signals_only DataException;
	 @*/
	public void add(Dominio obj) throws DataException;
	
	/*@
	 @	public normal_behavior 
	 @		requires obj != null;
	 @		requires ((long) obj.getCodigo()) > 0;
	 @		requires list.isEmpty() == false;
	 @		requires list.contains(obj);
	 @ 		assignable list;	 
	 @		ensures !list.contains(obj);
	 @	also
	 @	public exceptional_behavior
	 @		requires obj == null || ((long) obj.getCodigo()) <= 0 || list.isEmpty() || list.contains(obj) == false;
	 @		assignable \nothing;
     @		signals_only DataException;
	 @*/
	public void remove(Dominio obj) throws DataException;
	
	/*@
	 @	public normal_behavior 
	 @		requires obj != null;
	 @		requires list.contains(obj);
	 @ 		assignable list;
	 @		ensures	list.contains(obj);
	 @		ensures obj.getCodigo() == \old(obj.getCodigo());	 
	 @	also
	 @	public exceptional_behavior
	 @		requires obj != null;
	 @		requires !list.contains(obj);
	 @		signals_only DataException;
	 @		signals (DataException e)
	 @			list.isEmpty() || list.contains(obj) == false;
	 @*/	
	public void update (Dominio obj) throws DataException;
	
	/*@
	 @	public normal_behavior 
	 @		requires ((long) codigo) > 0;
	 @		requires this.exists(codigo);
	 @		ensures \result != null;
	 @		ensures \result.getCodigo() == codigo;
	 @	also
	 @	public exceptional_behavior 
	 @		requires ((long) codigo) > 0;
	 @		requires !this.exists(codigo);
	 @		signals_only DataException;
	 @	also
	 @	public exceptional_behavior
	 @		requires ((long) codigo) <= 0;
	 @		signals_only DataException;
	 @*/
	public /*@ pure @*/ Dominio get(long codigo) throws DataException;
	
	/*@ 
	 @	public normal_behavior 
	 @ 		requires list != null;
	 @		ensures \result != null;
	 @	also
	 @	public exceptional_behavior
	 @		requires list == null;
	 @		signals_only DataException;
	 @*/
	public /*@ pure @*/ List<Dominio> list() throws DataException;
	
	/*@
	 @ ensures ((long) codigo) <= 0 ==> \result == false;
	 @*/
	public /*@ pure @*/ boolean exists(long codigo);
	
	/*@
	 @ ensures ((long) obj.getCodigo()) <= 0 ==> \result == false;
	 @*/
	public boolean exists(Dominio obj);
		
	public void clear();
	
	

}
