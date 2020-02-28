package com.teradata.appserver.mulitpledb.h2;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Sqle_Function")
@Getter
@RequiredArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SqleFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @EqualsAndHashCode.Include
    @Column(name = "FUNCTION_NAME")
    private String functionName;
    @EqualsAndHashCode.Include
    private String category;
    /*@JsonManagedReference
    @OneToMany(mappedBy = "sqleFunction", cascade = CascadeType.ALL)
    private Set<FunctionTime> times = new HashSet<>();*/

}
