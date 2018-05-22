# springRestService


Это проект быстрого способа построить "web service"
- **часть 1: качаем архитип приложения**
- у нас будет **spring boot**
- клонируем проект: "git clone https://github.com/spring-guides/gs-rest-service.git"
- открываем проект в "IDEA" (*) ВАЖНО выбрать либо "build.gradle" или "pom.xml"
- сбилдив проект, сделать исполняемым файл "\*.jar" и запускаем "java -jar ./\*.jar"
можно так " chmod +x ./build/libs/gs-rest-service-0.1.0.jar && java -jar ./build/libs/gs-rest-service-0.1.0.jar --server.port=8090 "
- проверяем ответ на запросс "GET" -> "http://localhost:8090/greeting"
- **часть 2: архитектура**
- "контроллеры" тут мапинг запросов на методы и вызов методов фасадов
- "фасады" тут обьединяется вызов методов различных сервисов
- "сервисы" это некие функциональные обособленные блоки логики
- "DAO" это уровень чтения/записи данных
- "model" представление данных в хранимом виде в БД 
- **часть 3: БД(H2)**
- мы выбираем "in memory database" "H2"
- добавляем зависимости:  
  1. "testCompile group: 'com.h2database', name: 'h2', version: '1.4.196'"  
  2. "compile group: 'org.springframework.boot', name: 'spring-boot-starter-jdbc', version: '1.5.12.RELEASE'"  
- создадим папку "resources" в "main" кинем туда "application.properties"

spring.datasource.url = jdbc:h2:~/test  
spring.datasource.username = sa  
spring.datasource.password = 
spring.datasource.driverClassName = org.h2.Driver 

\# H2  
spring.h2.console.enabled=true  
spring.h2.console.path=/h2-console  

- создодим 2-а своих бина для подключения к БД
```java
@SpringBootApplication
@SpringBootConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * в этот бин при создании прокидываются
     * параметры с префиксом [spring.datasource] из
     * [application.properties]
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSourceH2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplateH2(@Qualifier("dataSourceH2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
```
- теперь можем написать уровень DAO
```java
@Repository
public class CarDAO {

   final String ADD_CAR="INSERT INTO CAR(column1, column2) VALUES(?,?)";
   final String GET_ALL_CARS="SELECT * FROM CAR";

   @Qualifier("jdbcTemplateH2")
   @Autowired
   private JdbcTemplate jdbcTemplate;

   public void addCar(Car car) {
      jdbcTemplate.update(ADD_CAR, car);
   }

   public List<Car> getAllCars() {
       return jdbcTemplate.query(GET_ALL_CARS, new CarMapper());
   }
   
}
```
- мы используем отдельный класс мапера, для указания соответствия полей модели
и полей сущности в БД "RowMapper()"
```java
public class CarMapper implements RowMapper<Car> {

    public Car mapRow(ResultSet rs, int rows) throws SQLException {
        Car car = new Car();

        car.setId(rs.getInt("id"));
        car.setModel(rs.getString("modev"));
        car.setVendor(rs.getString("vendor"));

        return car;
    }

}
```
- теперь можем при запуске сходить в консоль "http://localhost:8080/h2-console"
- **часть 4: хакатон**
- сохранили ссылку на хакатон "https://docs.google.com/spreadsheets/d/1hg0pTWDfZ8AYc2HLC9wK30QC1i7VmbLPqInOeVDuMvk/edit#gid=0"
- сделаем ф-ионал описанный в задании + его расширим
- ! доделать ф-ионал
- **часть 5: как скачать и запустить этот проект**
- качаем "git clone https://github.com/dzhukov40/springRestService.git"
- собираем "gradle build"
- запускаем ""
- подключаемся к базе "http://localhost:8080/h2-console"
- запуск "chmod +x ./build/libs/gs-rest-service-0.1.0.jar && java -jar ./build/libs/gs-rest-service-0.1.0.jar "
- **часть 6: разбираемся с Jenkins, настраиваем CI "непрерывная интеграция"**
- устанавливаем "JENKINS"
```
1)смотрим как установить на [linux] 
  - [https://jenkins.io/doc/pipeline/tour/getting-started/]
  - качаем: [ wget -q mirrors.jenkins.io/war-stable/latest/jenkins.war ]
  - запускаем: [ java -jar jenkins.war --httpPort=8080 ]
    - надо установить [java]
    - установим оракловую:
    - [ sudo add-apt-repository ppa:webupd8team/java && sudo apt update ]
    - [ sudo apt install oracle-java8-installer ]
    - [ java -version ]
```    
- разбираемся как все тут настроить то:
```
2) стучимся и поехали
  - указываем ключ, который генериться на сервере, 
    на котором запущен [jenkins] -> [ cat /home/proxy40/.jenkins/secrets/initialAdminPassword ] 
  - Чтобы начать установку стандартного набора плагинов, нажмите Install suggested plugins.
  - Create First Admin User
    - Username: dzhukov
    - Password: 1111
    - Confirm Password: 1111
    - Full name: dzhukov   
    - E-mail address: dscjncrbq@mail.ru
  -...
  - рестартуем [jenkins]
  - заходим как пользователь [ dzhukov/1111 ]
 
3) пробуем создать задание 
  - new item
  - Enter an item name: [springRestService]
  - выбираем [Freestyle project]
  - [Source Code Management]
    - выбираем [Git]
    - указываем репозиторий [https://github.com/dzhukov40/springRestService]
    - [!] будет ошибка, указываем пользователя и пароль 
      - [ dzhukov40/D....... ]
    - не помогло, так как не установлен [git]
      - [ sudo apt install git -y ]
      - (*) обновили страничку, все прокатило !
    - теперь идем в [Build]
      - добавляем шаг сборки [ Add build step ]
        - [ Invoke Gradle Script ]
        - [ Use Gradlew Wrapper ] (*) Make gradle executable
        - [ Tasks ] -> [ clean build ]
        - (*) Результат сборки будет сохраняться в папке /var/lib/jenkins/workspace/Lolipop/target/, где Lolipop — название нашего проекта
      - добавляем шаг для запуска [ Add build step ] 
        - (*) запустить "shell" скриптик [ Execute shell ]   
          - забираем на части скриптик:
            - убиваем [ if pgrep gs-rest-service-0.1.0.jar; then pkill gs-rest-service-0.1.0.jar; fi ]
            - копируем [ yes | cp -rf '/home/proxy40/.jenkins/workspace/springRestService/build/libs/gs-rest-service-0.1.0.jar' '/home/proxy40/My/app' ]
            - делаем исполняемым [ chmod +x '/home/proxy40/My/app/gs-rest-service-0.1.0.jar' ]
            - запускаем и отпускаем
              - (*) надо указать не убивать процесс [BUILD_ID=do_not_kill_me]
              - наш запуск [ BUILD_ID=do_not_kill_me java -jar '/home/proxy40/My/app/gs-rest-service-0.1.0.jar' --server.port=8090 & ]
          - [!] полный текст команды состоит из этих команд записанных с новой строчки   
      - СОХРАНИТЬ

4) настроили теперь смотрим
  - [ Build Now ] смотрим тут сборки
```
    
 