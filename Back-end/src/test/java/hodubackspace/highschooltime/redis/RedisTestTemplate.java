package hodubackspace.highschooltime.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hodubackspace.highschooltime.api.controller.dto.request.RequestLoginMemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootTest
public class RedisTestTemplate {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void redisOpsForValueTest() throws Exception {
        //given
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        //when
        valueOperations.set("test", "psh");

        Object test2 = valueOperations.get("test2");
        System.out.println("test2 = " + test2);

        //then
        Assertions.assertThat(valueOperations.get("test")).isEqualTo("psh");
    }

    @Test
    public void redisOpsForHashTest() throws Exception {
        //given
        String key = "KEY";
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //when
        valueOperations.set(key,  new RequestLoginMemberDto("test@test.com","12345678").toString());
        valueOperations.set(key,  new RequestLoginMemberDto("test2@test.com", "12345678").toString());

        //then
        Object objects = valueOperations.get(key);

        System.out.println("objects = " + objects.toString());

        RequestLoginMemberDto data = getHashData(objects.toString(), RequestLoginMemberDto.class);
        System.out.println("data = " + data);

    }

    public static <T> T getHashData(String objectString, Class<T> clazz) throws JsonProcessingException {

        if(!StringUtils.hasText(objectString)){
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(objectString, clazz);
    }

}
