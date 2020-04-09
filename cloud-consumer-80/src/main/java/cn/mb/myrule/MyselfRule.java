package cn.mb.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 由于ribbon要求，不要将Rule放在@ComponengScan能扫描的到的地方，否则该配置就会被所有ribbon客户端共享
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }

}
