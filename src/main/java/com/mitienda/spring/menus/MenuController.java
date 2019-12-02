package com.mitienda.spring.menus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.mitienda.spring.controllers.CategoryController;
import com.mitienda.spring.controllers.ClienteController;
import com.mitienda.spring.controllers.FacturaController;
import com.mitienda.spring.controllers.FacturaLineaController;
import com.mitienda.spring.controllers.ProductoController;
import com.mitienda.spring.models.Categoria;
import com.mitienda.spring.models.Clientes;
import com.mitienda.spring.models.Factura;
import com.mitienda.spring.models.FacturaLinea;
import com.mitienda.spring.models.Producto;

public class MenuController {
	
	private Menu menu;
	private Scanner keyboard;
	private static MenuController instance;
	
	private static CategoryController catCtrl = CategoryController.getInstance();
	private static ClienteController cliCtrl  = ClienteController.getInstance();
	private static FacturaController facCtrl = FacturaController.getInstance();
	private static ProductoController  prodCtrl = ProductoController.getInstance();
	private static FacturaLineaController flCtrl = FacturaLineaController.getInstance();
	
	
	
	private MenuController() {
		this.keyboard = new Scanner(System.in);
	}

	public static MenuController getInstance() {
		if (instance == null) {
			instance = new MenuController();
		}
		return instance;
	}
	public void callMenus() {
		menu = new MenuPrincipal();
		while(menu!=null) {
			navegarMenu();
		}
		keyboard.close();
		System.out.println("ByeBye");		
	}
	public  Scanner getKeyboard(){
		return this.keyboard;
	}
	

	public void navegarMenu() {
		String opcion;
		System.out.println(menu.toString());
		opcion = this.keyboard.nextLine();
		menu = menu.siguienteMenu(opcion);
	}
	
	private String validarCampo(String regex) {		
		String datos;	
		datos = keyboard.nextLine();		
		
		while (!Pattern.matches(regex, datos)) {		
				System.out.println("El valor introducido para el campo no es v�lido");
				datos = keyboard.nextLine();										
		}
		return datos;
	}
	
	
	public static String campoValido(String regex) {
		return MenuController.getInstance().validarCampo(regex);
	}	
	
	public static Factura eligeFactura() {		
			
			int id=-1;
			List<Factura> tabla = facCtrl.list();
			if (tabla.isEmpty()) {
				System.out.println("No hay nada en la tabla");
				return null;
			}
			
			for(Factura dbo: tabla ) {	
				System.out.println(dbo.getId()+" "+dbo.toString());
			}
			System.out.println("Introduzca la ID del objeto de Factura que quiera seleccionar ");
			try {
				id = Integer.parseInt(MenuController.getInstance().keyboard.nextLine());
			}catch(NumberFormatException ex){
				System.out.println("Id no v�lida");
				return null;
			}
			Factura pedido = facCtrl.findById((long) id);	
			
			return pedido; 
	}
	
	public static FacturaLinea eligeLineaFactura() {	
		int id;
		Factura fac = MenuController.eligeFactura();
		List<FacturaLinea> listAllFl = flCtrl.list();
		List<FacturaLinea> listFl = new ArrayList<>();
		
		
		for(FacturaLinea obj: listAllFl) {
			if(obj.getId_factura() == fac.getId()) {
				listFl.add(obj);
			}
		}
		
		for(FacturaLinea obj: listFl) {
			System.out.println(obj.getId()+" "+obj.toString());
		}
		System.out.println("Introduzca la ID del objeto de FacturaLinea que quiera seleccionar ");
		try {
			id = Integer.parseInt(MenuController.getInstance().keyboard.nextLine());
		}catch(NumberFormatException ex){
			System.out.println("Id no v�lida");
			return null;
		}
		FacturaLinea pedido = flCtrl.findById((long) id);	
		
		return pedido; 
		
	}
	
	public static Clientes eligeCliente() {
		int id=-1;
		List<Clientes> tabla = cliCtrl.list();
		if (tabla.isEmpty()) {
			System.out.println("No hay nada en la tabla");
			return null;
		}
		
		for(Clientes dbo: tabla ) {	
			System.out.println(dbo.getId()+" "+dbo.toString());
		}
		System.out.println("Introduzca la ID del objeto de Clientes que quiera seleccionar ");
		try {
			id = Integer.parseInt(MenuController.getInstance().keyboard.nextLine());
		}catch(NumberFormatException ex){
			System.out.println("Id no v�lida");
			return null;
		}
		Clientes pedido = cliCtrl.findById((long) id);	
		
		return pedido; 
	}
	
	public static Producto eligeProducto() {
		int id=-1;
		List<Producto> tabla = prodCtrl.list();
		if (tabla.isEmpty()) {
			System.out.println("No hay nada en la tabla");
			return null;
		}
		
		for(Producto dbo: tabla ) {	
			System.out.println(dbo.getId()+" "+dbo.toString());
		}
		System.out.println("Introduzca la ID del objeto de Productos que quiera seleccionar ");
		try {
			id = Integer.parseInt(MenuController.getInstance().keyboard.nextLine());
		}catch(NumberFormatException ex){
			System.out.println("Id no v�lida");
			return null;
		}
		Producto pedido = prodCtrl.findById((long) id);	
		
		return pedido; 
	}
	
	public static Categoria eligeCategoria() {
		int id=-1;
		List<Categoria> tabla = catCtrl.list();
		if (tabla.isEmpty()) {
			System.out.println("No hay nada en la tabla");
			return null;
		}
		
		for(Categoria dbo: tabla ) {	
			System.out.println(dbo.getId()+" "+dbo.toString());
		}
		System.out.println("Introduzca la ID del objeto de Productos que quiera seleccionar ");
		try {
			id = Integer.parseInt(MenuController.getInstance().keyboard.nextLine());
		}catch(NumberFormatException ex){
			System.out.println("Id no v�lida");
			return null;
		}
		Categoria pedido = catCtrl.findById((long) id);	
		
		return pedido; 
	}

	public static Date validarFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		Date fecha = new Date();
		String datos;
		boolean validado = false;
		
		datos = MenuController.getInstance().getKeyboard().nextLine();
		
		while(!validado) {
			try {
				fecha = sdf.parse(datos);
				validado = true;
			} catch (ParseException e) {
				System.err.println("Error: el formato de la fecha debe ser: yyyy/MM/dd");
				e.printStackTrace();
			}
		}
		return fecha;
	}
	
}
