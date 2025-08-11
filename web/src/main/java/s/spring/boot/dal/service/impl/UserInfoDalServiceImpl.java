package s.spring.boot.dal.service.impl;

import s.spring.boot.dal.entity.UserInfoEntity;
import s.spring.boot.dal.mapper.UserInfoMapper;
import s.spring.boot.dal.service.UserInfoDalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chensihong
 * @since 2024-03-20
 */
@Service
public class UserInfoDalServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoDalService {

}
