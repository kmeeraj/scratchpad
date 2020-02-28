package com.teradata.appserver.mulitpledb.h2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "Function_Time")
@Getter
@RequiredArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FunctionTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "TIME_TAKEN_MS")
    private Long timeTakenMs;
    @EqualsAndHashCode.Include
    private String cloud;
    private Integer errorCode;
    private String errorMessage;
    private String query;
    @EqualsAndHashCode.Include
    @Column(name = "FUNCTION_NAME")
    private String functionName;
   /* @ManyToOne
    @JoinColumn(name = "FUNCTION_NAME", nullable = false)
    @JsonBackReference
    private SqleFunction sqleFunction;*/
}
