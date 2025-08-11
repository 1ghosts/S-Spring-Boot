package s.spring.boot.common.utils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

import java.util.List;

/**
 * @author chensihong
 * @version 1.0
 * @date 2024/3/11 13:41
 */
public class BeanCopyUtil {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private BeanCopyUtil(){}

    public static <S, D> D copy(S source, Class<D> aClass) {
        return Mapper.INSTANCE.map(source, aClass);
    }


    public static <S, D> List<D> mapAsList(List<S> source, Class<D> aClass) {
        return Mapper.INSTANCE.mapAsList(source, aClass);
    }

    public static <S, D> D copy(S var1, Type<S> formType, Type<D> toType) {
        return Mapper.INSTANCE.map(var1, formType, toType);
    }

    enum Mapper {

        /**
         * 单例
         */
        INSTANCE;

        private final MapperFacade mapperFacade;

        Mapper() {
            this.mapperFacade = BeanCopyUtil.MAPPER_FACTORY.getMapperFacade();
        }

        public <S, D> D map(S source, Class<D> aClass) {
            return mapperFacade.map(source, aClass);
        }

        public <S, D> D map(S var1, Type<S> formType, Type<D> toType) {
            return mapperFacade.map(var1, formType, toType);
        }

        public <S, D> List<D> mapAsList(List<S> source, Class<D> aClass) {
            return mapperFacade.mapAsList(source, aClass);
        }
    }
}
