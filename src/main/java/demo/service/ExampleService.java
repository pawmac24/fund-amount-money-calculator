package demo.service;

import org.springframework.stereotype.Component;

@Component
public class ExampleService {

    public String findParcelNumber(){
        String parcelNo = "00123";
        System.out.println("findParcelNumber " + parcelNo);
        return parcelNo;
    }
}
