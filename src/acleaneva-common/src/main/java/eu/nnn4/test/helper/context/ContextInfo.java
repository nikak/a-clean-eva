package eu.nnn4.test.helper.context;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContextInfo {
    private final static Logger log = Logger.getLogger(ContextInfo.class.getName());

    public static void printContextLoaded(ApplicationContext applicationContext, String mpackage) {
        printLoadedProperties(applicationContext);
        printLoadedFilteredBeans(applicationContext, mpackage);
    }

    public static void printLoadedBeans(ApplicationContext applicationContext) {
        log.info("all loaded beans: ");
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }

    public static void printLoadedProperties(ApplicationContext applicationContext) {
        log.info("active profiles: " + Arrays.toString(applicationContext.getEnvironment().getActiveProfiles()));
        Properties properties = ContextInfo.applicationProperties(applicationContext.getEnvironment());
        log.info("keys of loaded properties: " + Arrays.toString((properties.entrySet().toArray())));
    }

    public static void printLoadedFilteredBeans(ApplicationContext applicationContext, String mpackage) {
        final List<String> beansFiltered = getLoadedFilteredBeans(applicationContext, mpackage);
        log.info("Package: " + mpackage + ", beans loaded in context, size: " + beansFiltered.size());
        log.log(Level.FINER, "beans loaded in context: " + beansFiltered.toString());
        log.info("active profiles: " + Arrays.toString(applicationContext.getEnvironment().getActiveProfiles()));
    }

    public static boolean loadedFilteredBeansGreaterThan(ApplicationContext applicationContext, String mpackage,
                                                         int min) {
        return getLoadedFilteredBeans(applicationContext, mpackage).size() > min;
    }


    public static void printActiveProperties(ConfigurableEnvironment env) {
        System.out.println("************************* APP PROPERTIES");
        Map<String, MapPropertySource> propertySourceMap = new HashMap<>();
        env.getPropertySources().forEach(it -> {
            if (it instanceof MapPropertySource) {//&& it.getName().contains("applicationConfig")
                propertySourceMap.put(it.getName(), (MapPropertySource) it);
            }
        });
        for (String sourceName : propertySourceMap.keySet()) {
            log.info("propertySourceName: " + sourceName +
                    "\n---------------------------------------");
            propertySourceMap.get(sourceName).getSource().keySet()
                    .stream().filter(key ->
                                    !key.toLowerCase().contains("java")
//                            &&(key.contains("acleaneva")||key.contains("spring"))
                    )
                    .distinct()
                    .sorted()
                    .forEach(key -> {
                        try {
                            System.out.println(key + "=" + env.getProperty(key));
                        } catch (Exception e) {
                            System.out.println(key + " -> " + e.getMessage());
                        }
                    });
        }
        System.out.println("*******************************");
    }

    private static List<String> getLoadedFilteredBeans(ApplicationContext applicationContext, String mpackage) {
        String[] allBeans = applicationContext.getBeanDefinitionNames();
        Arrays.sort(allBeans);

        final List<String> beansFiltered = Stream.of(allBeans)
                .filter(a -> applicationContext.getBean(a)
                        .getClass().getName().contains(mpackage))
                .collect(Collectors.toList());
        return beansFiltered;
    }

    private static Properties applicationProperties(Environment environment) {
        final Properties properties = new Properties();
        for (Iterator it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof PropertiesPropertySource) {
                log.info("Adding all properties contained in " + propertySource.getName());
                properties.putAll(((MapPropertySource) propertySource).getSource());
            }
            if (propertySource instanceof CompositePropertySource) {
                properties.putAll(getPropertiesInCompositePropertySource((CompositePropertySource) propertySource));
            }
        }
        return properties;
    }

    private static Properties getPropertiesInCompositePropertySource(CompositePropertySource compositePropertySource) {
        final Properties properties = new Properties();
        compositePropertySource.getPropertySources().forEach(propertySource -> {
            if (propertySource instanceof MapPropertySource) {
                log.info("Adding all properties contained in " + propertySource.getName());
                properties.putAll(((MapPropertySource) propertySource).getSource());
            }
            if (propertySource instanceof CompositePropertySource) {
                properties.putAll(getPropertiesInCompositePropertySource((CompositePropertySource) propertySource));
            }
        });
        return properties;
    }
}
