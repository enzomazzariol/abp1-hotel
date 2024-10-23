package servlets;

import service.MisReservasService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
;

@WebServlet("/misReservas")
public class MisReservasServlet extends HttpServlet {
    public MisReservasService misReservasService;

    public MisReservasServlet() {
        misReservasService = new MisReservasService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        misReservasService.fowardReservaActividad(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        misReservasService.menuPostMisReservas(req, resp);
    }
}
