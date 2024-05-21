// package com.clientside.mbkm.Controllers;

// import org.springframework.security.authentication.AnonymousAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.clientside.mbkm.Models.dto.request.LoginRequest;
// import com.clientside.mbkm.Services.AuthService;
// import com.clientside.mbkm.utils.AuthSessionUtil;

// import lombok.AllArgsConstructor;

// @AllArgsConstructor
// @Controller
// @RequestMapping("/loginn")
// public class AuthController {
//     private AuthService authService;

//     @GetMapping
//     public String loginView(LoginRequest loginRequest) {
//         Authentication auth = AuthSessionUtil.getAuthentication();

//     if (auth instanceof AnonymousAuthenticationToken) {
//       return "auth/login";
//     }
//     return "redirect:/home";
//   }

//     @PostMapping
//     public String login(LoginRequest loginRequest) {
//         if (!authService.login(loginRequest)) {
//             return "redirect:/login?error=true";
//         }
//         return "redirect:/home";
//     }
// }