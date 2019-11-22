package com.mitienda.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitienda.spring.models.Categoria;
import com.mitienda.spring.repositories.CategoriasRepository;
 

public class CategoryController {

	private static CategoryController instance;

	private CategoryController() {
	}

	public static CategoryController getInstance() {
		if (instance == null) {
			instance = new CategoryController();
		}
		return instance;
	}

	
	private CategoriasRepository repository = new CategoriasRepository() {
		
		@Override
		public <S extends Categoria> Iterable<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <S extends Categoria> S save(S entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Optional<Categoria> findById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<Categoria> findAllById(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<Categoria> findAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean existsById(Long id) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void deleteById(Long id) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll(Iterable<? extends Categoria> entities) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void delete(Categoria entity) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}
	};

	/**
	 * Guarda o Actualiza un objecto
	 * @param cat
	 * @return 
	 */
	public Categoria save(Categoria cat) {
		return repository.save(cat);
	}
	
	/**
	 * 
	 * @return Devuelve el numero de Items que tenemos en la base de datos
	 */
	public long count() {
		return repository.count();
	}

	/**
	 * 
	 * @return Devuelve todos los Objectos de la Tabla
	 */
	public List<Categoria> list() { 
		ArrayList<Categoria> ret = new ArrayList<>();
	    for(Categoria t : repository.findAll()) {
	        ret.add(t);
	    }
	    if (ret.isEmpty()) {
	    	System.out.println("NO HAY NADA QUE DAR");
	    	return null;
	    }
	    return ret;
	}

	/**
	 * Borra de la base de datos el objecto que se le pasa por parametro
	 * @param cat - <strong>TIENE QUE TENER ID</strong>
	 */
	public void delete(Categoria cat) {
        repository.delete(cat);
	}
	
	/**
	 * Borra de la base de datos el objecto con el ID que se le pasa por parametro
	 * @param id
	 */
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return Devuelve el Objecto que corresponda con el ID o null en caso de no existir en la base de datos
	 */
	public Categoria findById(Long id) {
		Optional<Categoria> item = repository.findById(id);
        // item..ifPresent(x -> System.out.println(x)); // Devuelve el objecto si existe
		if (!item.isPresent()) {
			return null;
		}
        return item.get();
	}
	
	
}
