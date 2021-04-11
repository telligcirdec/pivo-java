package net.gillets.pivo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.gillets.pivo.dao.UserDao;
import net.gillets.pivo.domain.User;
import net.gillets.pivo.domain.data.UserData;

@SpringBootTest(classes = PivoApplication.class)
@ActiveProfiles("test")
public class PivoApplicationTest {
	
	@Autowired
	private UserDao userDao;

    @Test
	public void contextLoads() {

		User user = new User(new UserData("Youhou"),true);
		userDao.save(user);

		Assertions.assertThat(true).isTrue();
	}

}
