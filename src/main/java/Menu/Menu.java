package Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import FuncionesMenu.FuncionesMenu;

public class Menu {
	
	public static void menu() {
		int caso = 0;
		Scanner sc = new Scanner (System.in);
		
		do {

			System.out.println("1/Anadir un cliente");
			System.out.println("2/Mostrar un cliente");
			System.out.println("3/Mostrar todos los clientes");
			System.out.println("4/Buscar un cliente");
			System.out.println("0/Salir del programa");
		
			try {
				caso = sc.nextInt();
			}catch (InputMismatchException e){
				
				System.out.println("Debe introducir un numero, y que este comprendido entre 0 y 4");
				menu();
			}
			

			switch (caso) {
			case 1:
				FuncionesMenu.anadirClientes();
				break;
			case 2:
				FuncionesMenu.mostrarCliente();
				break;
			case 3:
				FuncionesMenu.mostrarTodosLosClientes();
				break;
			case 4:
				FuncionesMenu.buscarClientes();
				break;
			
			}
		} while (caso != 0);
	}
}



