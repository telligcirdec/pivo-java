package net.gillets.pivo.dao.entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.gillets.pivo.PivoApplication;
import net.gillets.pivo.dao.pivo.PivoEntityDaoTest;
import net.gillets.pivo.domain.entity.Domain;
import net.gillets.pivo.domain.entity.data.DomainData;

@SpringBootTest(classes = PivoApplication.class)
@ActiveProfiles("test")
public class DomainDaoTest extends PivoEntityDaoTest<DomainDao, Domain, DomainData> {

    @Autowired
    public DomainDaoTest(DomainDao domainDao) {
        super(domainDao);
    }

    @Override
    protected Class<Domain> getEntityClass() {
        return Domain.class;
    }

    @Override
    protected DomainData getData(boolean testFailure) {
        if(!testFailure){
            String randomUsername = RandomStringUtils.randomAlphabetic(10);

            return DomainData.builder().
                    domainName(randomUsername)
                    .build();
        } else{
            return  DomainData.builder().
            domainName(RandomStringUtils.randomAlphabetic(60))
            .build();

        }
    }

    @Override
    protected String getEntityTypeCode() {
        return Domain.ENTITY_NAME;
    }
    

}
