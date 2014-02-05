

#Catálogo de Tecnologia
 

##Instruções para intalação

###O banco de dados

Escolha o banco de sua preferencia, nós utilizamos o Postgresql.

Crie uma base chamada "catalogoTecnologia" e altere o seu persistence.xml ou o DataSource no Jboss

Se você escolheu outro banco não esqueça de alterar a dependencia 
no pom.xml, para seu banco.

        <dependency>
            <groupId>postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>9.1-901-1.jdbc4</version>
        </dependency> 


##Configurando e Instalando no Tomcat7

Para compilar escolha o perfil tomcat7 (Eclipse e Netbeans)

##Configurando e Instalando no JBoss 7 

O seguinte trecho do web.xml deve ser comentado:
    
    <listener>
        <listener-class>org.jboss.weld.environment.servlet.Listener</listener-class>
    </listener>
    
Crie um módulo para seu driver JDBC no jboss:

 Crie a seguinte estrutura de diretórios $JBOSS_HOME/modules/org/postgresql/main
 Crie um arquivo module.xml no diretório main com o seguinte conteúdo
		
	<?xml version="1.0" encoding="UTF-8"?>
	  <module xmlns="urn:jboss:module:1.0" name="org.postgresql">
	  <resources>
	    <resource-root path="postgresql-9.1-902.jdbc4.jar"/>
	  </resources>
	  <dependencies>
	    <module name="javax.api"/>
	    <module name="javax.transaction.api"/>
	  </dependencies>
	</module>
		
Observe que path na tag resource-root corresponde ao nome do jar do driver
		
Copie o driver jdbc para o diretório main. Ele pode ser obtido em [http://jdbc.postgresql.org](http://jdbc.postgresql.org/download.html)
	
A estrutura deve ficar assim:

	org
	|_ postgresql
	|   |_ main
	|   |   |_ module.xml
	|   |   |_ postgresql-9.1-902.jdbc4.jar
	
Declare seu driver no standalone.xml
	No arquivo standalone.xml ($JBOSS_HOME/standalone/configuration/standalone.xml) localize a tag <drivers> e insira o seguinte trecho:
			dentro dela haverá uma tag  <datasources>. Insira o seguinte trecho dentro de <datasources>:
	
    <driver name="postgresql" module="org.postgresql">
        <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
    </driver>
	
Crie um DataSource para sua conexão JDBC no standalone.xml  
	No arquivo standalone.xml, junto com a declaração do driver, insira o seguinte trecho:
	
	<datasource jta="true" jndi-name="java:jboss/datasources/guddiDS" pool-name="guddiDS" enabled="true" use-ccm="false">
        <connection-url>jdbc:postgresql://desenv-db01/guddi</connection-url>
        <driver-class>org.postgresql.Driver</driver-class>
        <driver>postgresql</driver>
        <security>
            <user-name>usuario</user-name>
            <password>senha</password>
        </security>
        <validation>
            <validate-on-match>false</validate-on-match>
            <background-validation>false</background-validation>
        </validation>
        <statement>
            <share-prepared-statements>false</share-prepared-statements>
        </statement>
    </datasource>
	
	altere as credenciais.


Inicie o Jboss e faça o build da aplicação utilizando o seguinte comando. O maven fará o deploy no jboss 
	
	mvn install	-P jboss7
		

Para compilar escolha o perfil jboss7 (Eclipse e Netbeans)

