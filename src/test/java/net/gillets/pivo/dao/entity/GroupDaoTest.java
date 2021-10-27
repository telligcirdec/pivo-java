package net.gillets.pivo.dao.entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.gillets.pivo.PivoApplication;
import net.gillets.pivo.dao.pivo.PivoEntityDaoTest;
import net.gillets.pivo.domain.entity.Group;
import net.gillets.pivo.domain.entity.data.GroupData;

@SpringBootTest(classes = PivoApplication.class)
@ActiveProfiles("test")
public class GroupDaoTest extends PivoEntityDaoTest<GroupDao, Group, GroupData> {

    @Autowired
    public GroupDaoTest(GroupDao groupDao) {
        super(groupDao);
    }

    @Override
    protected Class<Group> getEntityClass() {
        return Group.class;
    }

    @Override
    protected GroupData getData(boolean testFailure) {
        if(!testFailure){
            String randomUsername = RandomStringUtils.randomAlphabetic(10);

            return GroupData.builder().
                    groupname(randomUsername)
                    .build();
        } else{
            return  GroupData.builder().
            groupname(RandomStringUtils.randomAlphabetic(60))
            .build();

        }
    }

    @Override
    protected String getEntityTypeCode() {
        return Group.ENTITY_NAME;
    }
    

}
