package cn.sp.test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Ship
 * @version 1.0.0
 * @description:
 * @date 2021/11/09 16:16
 */
public class ValidationTest {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static void main(String[] args) {
        Car car = new Car(null, "粤B66668", 1);
        Set<ConstraintViolation<Car>> violationSet = validator.validate(car);
        System.out.println(violationSet.size());
        Iterator<ConstraintViolation<Car>> iterator = violationSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getMessage());
        }
    }


    public static class Car {

        @NotNull(message = "manufacturer不能为null")
        private String manufacturer;

        @NotNull
        @Size(min = 2, max = 14)
        private String licensePlate;

        @Min(2)
        private int seatCount;

        public Car(String manufacturer, String licensePlate, int seatCount) {
            this.manufacturer = manufacturer;
            this.licensePlate = licensePlate;
            this.seatCount = seatCount;
        }
    }
}
