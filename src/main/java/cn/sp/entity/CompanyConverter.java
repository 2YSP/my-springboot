package cn.sp.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyConverter {

    CompanyConverter INSTANCE = Mappers.getMapper(CompanyConverter.class);


    @Mappings({
            @Mapping(source = "company.createTime", target = "createdTime"),
            @Mapping(source = "company.employee.name", target = "employeeName"),
            @Mapping(source = "company.employee.age", target = "employeeAge"),
            @Mapping(source = "company.createTime", target = "createFormatTime",dateFormat = "yyyy-MM-dd HH:mm:ss")

    })
    CompanyDTO domain2DTO(Company company);
}
