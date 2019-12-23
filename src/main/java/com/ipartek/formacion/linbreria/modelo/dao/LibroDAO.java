package com.ipartek.formacion.linbreria.modelo.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.spi.ErrorCode;

import raulcom.ipartek.formacion.libreria.exceptions.BorrarLibroException;
import raulcom.ipartek.formacion.libreria.modelo.pojo.Libro;

public class LibroDAO implements IDAO<Libro> {

	private static LibroDAO INSTANCE;
	private ArrayList<Libro> registros;
	private int indice = 1;

	private LibroDAO() {
		super();
		registros = new ArrayList<Libro>();

		// 3 libros hardcodeados de inicio
		registros.add(new Libro(indice++, "Los Futbolísimos: El misterio del jugador número 13", "Roberto Santiago", 11.95f,
				"https://images-na.ssl-images-amazon.com/images/I/51w8xn%2B1kNL._SX346_BO1,204,203,200_.jpg",
				5));
		registros.add(new Libro(indice++, "Los Futbolísimos: El  misterio de la tormenta de arena", "Roberto Santiago", 11.95f,
				"https://images-na.ssl-images-amazon.com/images/I/A105rksyCGL._AC_UL320_SR222,320_.jpg",
				5));
		registros.add(new Libro(indice++, "Los Futbolísimos: El  misterio del día de los inocentes", "Roberto Santiago", 11.95f,
				"https://images-na.ssl-images-amazon.com/images/I/91yHtKkzJuL.jpg",
				5));
	}

	public static synchronized LibroDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LibroDAO();
		}

		return INSTANCE;
	}

	@Override
	public List<Libro> getAll() {
		return registros;
	}

	@Override
	public Libro getById(int id) {
		Libro resultado = null;
		for (Libro libro : registros) {
			if (libro.getId() == id) {
				resultado = libro;
				break;
			}
		}
		return resultado;
	}

	@Override
	public Libro delete(int id) throws Exception {
		Libro resultado = null;
		boolean encontrado = false;
		for (Libro libro : registros) {
			if (libro.getId() == id) {
				resultado = libro;
				registros.remove(libro);
				encontrado = true;
				break;
			}
		}
		if (encontrado == false) {
			throw new BorrarLibroException();
		}
		return resultado;
	}

	@Override
	public Libro update(int id, Libro pojo) throws Exception {
		Libro resultado = null;
		for (Libro libro : registros) {
			if (libro.getId() == id) {
				libro = pojo;
				resultado = libro;
				break;
			}
		}
		return resultado;
	}

	@Override
	public Libro create(Libro pojo) throws Exception {
		Libro resultado = null;
		try {
			pojo.setId(indice++);
			registros.add(pojo);
			resultado = pojo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}
