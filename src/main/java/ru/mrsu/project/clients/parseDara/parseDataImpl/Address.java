package ru.mrsu.project.clients.parseDara.parseDataImpl;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.mrsu.project.clients.parseDara.parseData;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address implements parseData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private Integer house;
    @Column(name = "floor")
    private Integer floor;
    @Column(name = "flat_number")
    private Integer flatNumber;
    @OneToMany
    @JoinColumn(name = "address_id")
    private List<Client> clientId;

    public Address(HashMap<String, String> map) {
        setMap(map);
    }

    public Address(Integer id, String city, String street, Integer house, Integer floor, Integer flatNumber) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
        this.flatNumber = flatNumber;
    }

    @Override
    public String toString() {
        return "ID: " + id + " City: " + city + " House: " + house + " Floor: " + floor + " Flat number: " + flatNumber;
    }

    @Override
    public void setMap(HashMap<String, String> addressMap) {
        if(addressMap != null && !addressMap.isEmpty()) {
            this.id = Integer.parseInt(addressMap.get("id"));
            this.city = addressMap.get("city");
            this.street = addressMap.get("street");
            this.house = Integer.parseInt(addressMap.get("house"));
            this.floor = Integer.parseInt(addressMap.get("floor"));
            this.flatNumber = Integer.parseInt(addressMap.get("flatNumber"));
        }
    }

    public String getName() {
        return "Address";
    }

    public Boolean hasDuplicate(List<Object> addresses) {
        Integer count = 0;
        for (Object obj: addresses) {
            Address address = (Address) obj;
            if(address.floor.equals(this.floor) &&
            address.flatNumber.equals(this.flatNumber) &&
            address.street.equals(this.street) &&
            address.city.equals(this.city) &&
            address.house.equals(this.house))
                count++;
        }
        return count >= 2;
    }
}
