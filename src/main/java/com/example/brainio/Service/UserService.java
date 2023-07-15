package com.example.brainio.Service;

import com.example.brainio.Dtos.RequestFromFrontEndDto;
import com.example.brainio.Exception.Failure;
import com.example.brainio.Model.Form;
import com.example.brainio.Model.User;
import com.example.brainio.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private String convertToString(MultipartFile file) throws IOException {
        byte[] bytes = Base64.getEncoder().encode(file.getBytes());
        String res = Base64.getEncoder().encodeToString(bytes);
        return res;
    }

    public String saveFile(RequestFromFrontEndDto emp) throws IOException, Failure {
        Optional<User> userOptional = userRepository.findByEmail(emp.getEmail());
        Form form = new Form();
        form.setEmail(emp.getEmail());
        form.setName(emp.getName());
        form.setText(emp.getText());
        form.setRadio(emp.getRadio());
        form.setFile(convertToString(emp.getFile()));



        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(!user.getFormList().get(0).getName().equalsIgnoreCase(form.getName())) throw new Failure("incorrect name compared to previous form");
            user.getFormList().add(form);
            form.setUser(user);
            userRepository.save(user);
            return "success";
        }else{
            User user = new User();
            user.setEmail(form.getEmail());
            user.getFormList().add(form);
            form.setUser(user);
            userRepository.save(user);
            return "Success";
        }

    }
}
