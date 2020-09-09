package com.itmuch.usercenter.rocketmq;

import com.itmuch.usercenter.dao.user.BonusEventLogMapper;
import com.itmuch.usercenter.dao.user.UserMapper;
import com.itmuch.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.itmuch.usercenter.domain.entity.user.BonusEventLog;
import com.itmuch.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RocketMQMessageListener(consumerGroup = "consfumer-group", topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;
    private Integer bonus;

    @Override
    public void onMessage(UserAddBonusMsgDTO message) {

        //当收到消息的时候，执行业务
        //添加积分
        Integer userId = message.getUserId();
        bonus = message.getBonus();
        User user = this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus()+ bonus);
        this.userMapper.updateByPrimaryKeySelective(user);
        //添加日志
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userId)
                .value(bonus)
                .event("投稿")
                .createTime(new Date())
                .description("投稿加积分")
                .build());
    }
}
