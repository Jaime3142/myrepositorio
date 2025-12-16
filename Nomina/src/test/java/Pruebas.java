import com.example.nomina.Calculo;
import com.example.nomina.Empleado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pruebas {

 @Test
    void testSueldoBajo() {
        Empleado e = new Empleado("Empleado1", 15000, 0);
        double neto = Calculo.calcularNetoMensual(e);
        double esperado = 15000 / 12 * 0.9; // 10% IRPF mensual
        assertEquals(esperado, neto, 0.01);
    }

    @Test
    void testSueldoAlto() {
        Empleado e = new Empleado("Empleado2", 30000, 0);
        double neto = Calculo.calcularNetoMensual(e);
        double esperado = 30000 / 12 * 0.85; // 15% IRPF mensual
        assertEquals(esperado, neto, 0.01);
    }

   @Test
    void testAntiguedad() {
        Empleado e = new Empleado("Empleado3", 20000, 5);

        double mensual = 20000 / 12.0;
        double irpf = 20000 < 20000 ? 0.10 : 0.15; // 20000 ≥ 20000 → 15%
        double esperado = mensual * (1 - irpf) + 5 * 30; // suma antigüedad

        double neto = Calculo.calcularNetoMensual(e);
        assertEquals(esperado, neto, 0.01);
    }
}
