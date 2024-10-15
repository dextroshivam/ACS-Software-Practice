package com.acs.acs.Repository;

import com.acs.acs.ENUM.InventoryStage;
import com.acs.acs.Enitities.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository  extends JpaRepository<Container, Long> {

//    Container findOneByStatus(boolean status);

    Container findFirstByStatus(boolean b);
}
