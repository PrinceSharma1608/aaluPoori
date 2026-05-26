package tata.Machine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.Machines;

import java.util.List;

public interface MachinesRepository extends JpaRepository<Machines, String> {

    List<Machines> findByMachineStatus(Machines.MachineStatus machineStatus);

    List<Machines> findByArea_AreaId(String areaId);
}