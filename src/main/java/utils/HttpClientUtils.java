package utils;

import retrofit2.Call;
import retrofit2.Response;

public class HttpClientUtils {
    public static <T> T fetchFromExternal(final Call<T> call) {
        try {
            final Response<T> response = call.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException(
                        String.format("call failed with code : %s and error : %s",
                                response.code(),
                                response.errorBody().string()
                        )
                );
            }
            if (response.body() == null) {
                throw new RuntimeException(call + " response body null");
            }
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(call + " failed with error : " + e.getMessage(), e);
        }
    }
}
