package com.dev2023.resources.exceptions;
import com.dev2023.services.exception.StandarError;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
public class ValidationError extends StandarError {
    @Getter
    private List<FieldMessage> erros = new ArrayList<>();
    public void AddError(String filedName,String message){
        erros.add(new FieldMessage(filedName,message));
    }
}
