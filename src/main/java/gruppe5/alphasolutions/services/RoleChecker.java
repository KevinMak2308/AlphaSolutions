package gruppe5.alphasolutions.services;

import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.RoleRepository;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RoleChecker {
    public void roleChecker(Model model, HttpServletRequest request){
        RoleRepository roleRepo = new RoleRepository();
        DBManager.getConnection();
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("useremail");
        int accessRoles = roleRepo.checkRole(userEmail);
        model.addAttribute("roleID", accessRoles);
    }
}
