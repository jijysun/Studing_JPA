package HelloJPA.PracticeJPA.common.apiPayload;


import HelloJPA.PracticeJPA.common.apiPayload.code.BaseCode;
import HelloJPA.PracticeJPA.common.apiPayload.code.status.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder ({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty
    private boolean success;

    private String code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public static <T> ApiResponse<T> onSuccess(T result, SuccessStatus status) {
        return new ApiResponse<>(true, status.getCode(), status.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result) {
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), result);
    }

    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}