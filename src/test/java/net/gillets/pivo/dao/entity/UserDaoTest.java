package net.gillets.pivo.dao.entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.gillets.pivo.PivoApplication;
import net.gillets.pivo.dao.pivo.PivoEntityDaoTest;
import net.gillets.pivo.domain.entity.User;
import net.gillets.pivo.domain.entity.data.UserData;

@SpringBootTest(classes = PivoApplication.class)
@ActiveProfiles("test")
public class UserDaoTest extends PivoEntityDaoTest<UserDao, User, UserData> {

    @Autowired
    public UserDaoTest(UserDao userDao) {
        super(userDao);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected UserData getData(boolean testFailure) {
        if(!testFailure){
            String randomUsername = RandomStringUtils.randomAlphabetic(10);

            return UserData.builder().
                    username(randomUsername)
                    .build();
        } else{
            return  UserData.builder().
            username(RandomStringUtils.randomAlphabetic(60))
            .build();

        }
    }

    @Override
    protected String getEntityTypeCode() {
        return User.ENTITY_NAME;
    }
    

}
