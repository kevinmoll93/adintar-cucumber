package listeners;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import utils.RunReporte;

public class CustomReportListener implements ConcurrentEventListener {

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestRunFinished.class, event -> {
			System.out.println("Pruebas finalizadas. Generando reportes...");
			RunReporte.main(null); // Ejecuta el método principal de tu clase de reporte
		});
	}
}
