package br.com.ifpb.depsback.services;

import br.com.ifpb.depsback.model.User;
import br.com.ifpb.depsback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long idUser) {
        return userRepository.findById(idUser).get();
    }

    public User update(long idUser, User user) {
        User userDb = this.findById(idUser);
        userDb.setName(user.getName());
        userDb.setEmail(user.getEmail());
        userDb.setPassword(user.getPassword());
        User userAtualizado = userRepository.save(userDb);
        return userAtualizado;
    }

    public String delete(long idusu) {
        try {
            userRepository.deleteById(idusu);
            return "Deletado";
        }catch (Exception e){
            return "Ocorreu um problema: ";
        }
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

}
