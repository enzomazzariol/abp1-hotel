package Servlets;

import Model.ReservaActividad;
import Service.ReservaActividadService;
import Utils.Estado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/reservaActividad")
public class ReservaActividadServlet extends HttpServlet {

    ReservaActividadService ras;

    public ReservaActividadServlet(){
        ras = new ReservaActividadService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ras.fowardReservaActividad(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //ras.menuPostReservaActividad(req, resp);
            ras.listarReservaActividades(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
