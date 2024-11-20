package ma.srmanager.srparams.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentBioTime {

    private Long id;
    private String dept_code;
    private String dept_name;
    private String parent_dept;

}
