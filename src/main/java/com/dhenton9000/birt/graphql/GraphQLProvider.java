package com.dhenton9000.birt.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQLProvider {

    private static final Logger LOG = LoggerFactory.getLogger(GraphQLProvider.class);

    private GraphQL graphQL;

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        InputStream inr
                = this.getClass().getResourceAsStream("/schemas/schema.graphqls");
        String sdl = new BufferedReader(new InputStreamReader(inr))
                .lines().collect(Collectors.joining("\n"));

        // LOG.info("content " + sdl);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query").dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("officesById", graphQLDataFetchers.getOfficesByIdDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("getSalesReport", graphQLDataFetchers.getEmployeeSaleReportDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("getAnnualReport", graphQLDataFetchers.getAnnualReportDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("getSecurityGroups", graphQLDataFetchers.getSecurityGroupsDataFetcher()))
                .type(newTypeWiring("AnnualReport")
                          .dataFetcher("annualEarnings", graphQLDataFetchers.getAnnualTotalSalesDataFetcher()))
                //something wrong with the property case so duplicating
                .type(newTypeWiring("SecurityUsers")
                          .dataFetcher("userName", graphQLDataFetchers.userNameTranslator()))
                .build();
    }

}
