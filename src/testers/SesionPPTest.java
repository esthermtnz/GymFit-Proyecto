package testers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import aplicacion.Reserva;
import aplicacion.actividad.PlanPersonalizado;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.SesionPP;
import aplicacion.actividad.sesion.requisito.Requisito;
import aplicacion.sala.SalaSimple;
import aplicacion.usuario.Monitor;
import aplicacion.usuario.tarifa.TarjetaCredito;
import aplicacion.usuario.Cliente;
import aplicacion.actividad.sesion.requisito.*;

public class SesionPPTest {

	
	private SesionPP sesion;
	private PlanPersonalizado actividad;
	private SalaSimple salaSimple;
	private Monitor monitor;
	private Requisito requisito, requisito3, requisito4;
	private Reserva reserva;
	private Cliente cliente;
	private TarjetaCredito tarjetaCredito;
	
	@Before
	public void setUp() throws Exception{
		sesion = new SesionPP(LocalDate.now().plusDays(1), LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(6), actividad, salaSimple, 50.0);
		salaSimple = new SalaSimple("Pesas", "Para realizar levantamiento de pesas", 15);
		monitor = new Monitor ("Manu325", "00523821","gymManu", "Manuel", "manuel.guti@gmail.com");
		requisito = new RequisitoCancelaciones(0, 4);
		requisito3 = new RequisitoEdad(18, 40);
		requisito4 = new RequisitoVeterania(4, 15);
		cliente = new Cliente ("Paula", "698574213", LocalDate.of(1999, 03, 24), "pau24", "caracol9", tarjetaCredito);
		actividad = new PlanPersonalizado("Perdida de peso", monitor , requisito, TipoObjetivo.PERDIDAPESO, "para bajar de peso rapido");
		reserva = new Reserva(cliente, sesion, true);
		tarjetaCredito = new TarjetaCredito("4507670001000009","346", "Julia");
	}


	
	@Test
	public void testSetFecha1() {
		Boolean res = sesion.setFecha(LocalDate.now().plusDays(4));
		assertTrue(res);
	}
	
	@Test
	public void testSetFecha2() {
		Boolean res = sesion.setFecha(LocalDate.now().minusDays(31));
		assertFalse(res);
	}

	@Test
	public void testSetHoraIni1() {
		Boolean res = sesion.setHoraInicio(LocalDateTime.now().plusMinutes(5));
		assertTrue(res);
	}
	
	@Test
	public void testSetHoraIni2() {
		Boolean res = sesion.setHoraInicio(LocalDateTime.now().minusDays(2));
		assertFalse(res);
	}
	
	@Test
	public void testSetHoraFin1() {
		Boolean res = sesion.setHoraFin(LocalDateTime.now().plusHours(3));
		assertTrue(res);
	}
	
	@Test
	public void testSetHoraFin2() {
		Boolean res = sesion.setHoraFin(LocalDateTime.now().minusHours(4));
		assertFalse(res);
	}
	
	@Test
	public void testAddReserva1()
	{
		Reserva reserva = new Reserva(cliente, sesion, true);
		Boolean res = sesion.addReserva(reserva);
		assertTrue(res);
	}
	
	@Test
	public void testAddReserva2()
	{
		Reserva reserva = new Reserva(cliente, sesion, true);
		sesion.addReserva(reserva);
		Boolean res = sesion.addReserva(reserva);
		assertFalse(res);
	}
	
	@Test
	public void testAddReserva3()
	{
		Boolean res = sesion.addReserva(null);
		assertFalse(res);
	}
	
	@Test
	public void testRemoveReserva1() {
		Boolean res = sesion.removeReserva(null);
		assertFalse(res);
	}
	
	@Test
	public void testRemoveReserva2() {
		Reserva reserva = new Reserva(cliente, sesion, true);
		sesion.addReserva(reserva);
		Boolean res = sesion.removeReserva(reserva);
		assertTrue(res);
	}
	
	@Test
	public void testRemoveReserva3() {
		Reserva reserva = new Reserva(cliente, sesion, true);
		sesion.addReserva(reserva);
		sesion.removeReserva(reserva);
		Boolean res = sesion.removeReserva(reserva);
		assertFalse(res);
	}
	
	@Test
	public void testCalcularHorasSesion1()
	{
		Integer horas = sesion.calcularHorasSesion();
		assertEquals(2, horas, 0.0);
	}
	
	@Test
	public void testCalcularHorasSesion2()
	{
		SesionPP sesionPP = new SesionPP(LocalDate.now(), LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(1, 0)), actividad, salaSimple, 20.0);
		Integer horas = sesion.calcularHorasSesion();
		assertEquals(2,horas, 0.0);
	}
}
