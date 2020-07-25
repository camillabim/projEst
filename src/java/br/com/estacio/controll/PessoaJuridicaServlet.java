package br.com.estacio.controll;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.estacio.bean.PessoaJuridica;
import br.com.estacio.dao.PessoaJuridicaDAO;
import br.com.estacio.util.ValidacaoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Camilla Bim <camillabim@hotmail.com.br>
 */
public class PessoaJuridicaServlet extends HttpServlet {

    private PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();

    public PessoaJuridicaServlet() {
    }

    /**
     *
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PessoaJuridicaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PessoaJuridicaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String codigo = request.getParameter("codigo");

        try {
            if (acao != null && acao.equals("excluir")) {
                //trago o código diretamente da página JSP e converto-o em inteiro
                int codPessoaJuridica = Integer.parseInt(codigo);
                //chamo o método DAO excluir
                PessoaJuridicaDAO.excluir(codPessoaJuridica);
                //envio uma mensagem dizendo que a pj foi excluída com sucesso
                request.setAttribute("mensagem", "Pessoa Jurídica excluída com sucesso!");
            } else if (acao != null && acao.equals("editar")) {
                //trago o código diretamente da página JSP e converto-o em inteiro
                int codPessoaJuridica = Integer.parseInt(codigo);
                //recupero os dados atualizados do Id que desejo editar
                PessoaJuridica pj = pjdao.getPessoaJuridicaID(codPessoaJuridica);
                //solicito que seja setado os atributos da pessoa juridica
                request.setAttribute("pj", pj);
            }
        } catch (SQLException ex) {
            request.setAttribute("mensagem", "Erro de Banco de Dados " + ex.getMessage());
            Logger.getLogger(PessoaJuridicaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidacaoException ex) {
            request.setAttribute("mensagem", "Erro de Validação " + ex.getMessage());
            Logger.getLogger(PessoaJuridicaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            request.setAttribute("mensagem", "Erro de Classe Não Encontrada" + ex.getMessage());
            Logger.getLogger(PessoaJuridicaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            request.setAttribute("pjs", pjdao.getPessoaJuridica());
        } catch (Exception e) {
            request.setAttribute("mensagem", "Erro de Banco de Dados " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listarPessoaJuridica");
        dispatcher.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        String nomeFantasia = request.getParameter("nomeFantasia");
        String razaoSocial = request.getParameter("razaoSocial");
        String cnpj = request.getParameter("cnpj");
        String inscricaoEstadual = request.getParameter("inscricaoEstadual");
        String inscricaoMunicipal = request.getParameter("inscricaoMunicipal");
        String codigo = request.getParameter("codigo");

        PessoaJuridica pj = new PessoaJuridica(null, nomeFantasia, razaoSocial, cnpj, inscricaoEstadual, inscricaoMunicipal);

        if (codigo != null && !codigo.equals("")) {
            pj.setCodigo(Integer.parseInt(codigo));
        }
        try {
            //verifico se os campos estão validados, ou seja, preenchidos corretamente
            pj.valida();
            if (pj.getCodigo() != null) { 
                pjdao.atualizar(pj);
                request.setAttribute("mensagem", "Pessoa Jurídica Atualizada com Sucesso!");
            } else {//se nao, salvo pj
                //chamo o método DAO salvar Pessoa jurídica
                pjdao.salvar(pj);
                
                //Seto uma mensagem na tela dizendo que foi salvo com sucesso
                request.setAttribute("mensagem", "Pessoa Jurídica Salva com Sucesso!");
                //request.setAttribute("pj", pj);
            }
        } catch (SQLException ex) {
            request.setAttribute("mensagem", "Erro ao salvar Pessoa Jurídica");
            request.setAttribute("pj", pj);
            Logger.getLogger(PessoaJuridicaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidacaoException ex) {
            request.setAttribute("mensagem", "Erro de Validação dos Campos Obrigatórios");
            Logger.getLogger(PessoaJuridicaServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("pj", pj);
        }catch(NullPointerException ex){
            request.setAttribute("mensagem", "Erro NullPointerException");
            Logger.getLogger(PessoaJuridicaServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("pj", pj);
        }
        try {
            request.setAttribute("pjs", pjdao.getPessoaJuridica());
        } catch (SQLException ex) {
            request.setAttribute("mensagem", "Erro ao salvar Pessoa Jurídica");
            request.setAttribute("pj", pj);
            Logger.getLogger(PessoaJuridicaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listarPessoaJuridica");
        dispatcher.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
