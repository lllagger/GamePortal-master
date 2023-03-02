package com.GamePortal.Service;

import com.GamePortal.Entity.User;
import com.GamePortal.Repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired(required = false)
    private IUserRepository userInformationRepository;

    public String addUserInformation(User user) {
        userInformationRepository.save(user);
        log.warn("Kullanıcı veri tabanına kaydedildi");
        return "Yeni kullanıcı veri tabanına kaydedildi";
    }

    public User getUserInformation(Long id) {
        return userInformationRepository.findById(id).get();
    }

    public String deleteUserInformation(Long id) {
        User user = userInformationRepository.getById(id);
        userInformationRepository.delete(user);
        log.warn("Kullanıcı veri tabanından silindi");
        return id + " numaralı kullanıcı veri tabanından silindi";
    }

    public String updateUserInformation(User user) {
        userInformationRepository.save(user);
        log.warn("Kullanıcı bilgileri veri tabanında güncellendi.");
        return user.getUserId() + " numaralı kullanıcı bilgileri güncellendi";
    }


}
