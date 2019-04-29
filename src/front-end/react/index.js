import React from 'react';
import ReactDOM from 'react-dom';
import MainForm from './MainForm';
import ApolloClient from 'apollo-boost';
import { gql } from "apollo-boost";
 
const client = new ApolloClient({
  uri: "http://localhost:9000/graphql"
})


client.query({
  query: gql`
{
    getSalesReport(employeeId: 1702) {
    lastName
    firstName
    employeeNumber
    totalSales
  }
}


`
  
  
  
})
      .then(res => console.log(res))

ReactDOM.render(<MainForm />, document.querySelector('#graphqlDemo'));



