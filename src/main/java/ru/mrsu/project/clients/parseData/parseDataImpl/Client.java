package ru.mrsu.project.clients.parseData.parseDataImpl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mrsu.project.clients.parseData.parseData;

import jakarta.persistence.*;
import java.util.HashMap;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Client implements parseData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "personal_number")
    private String personalNumber;
    @Column(name = "address_id")
    private Integer addressId;

    public Client(HashMap<String, String> map) {
        setMap(map);
    }

    @Override
    public void setMap(HashMap<String, String> addressMap) {
        if(addressMap != null && !addressMap.isEmpty()) {
            this.id = Integer.parseInt(addressMap.get("id"));
            this.name = addressMap.get("name");
            this.personalNumber = addressMap.get("personnelNumber");
            this.addressId = Integer.parseInt(addressMap.get("addressId"));
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " Name: " + name + " Personnel number: " + personalNumber + " Address ID: " + addressId;
    }
}
