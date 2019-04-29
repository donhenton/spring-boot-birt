import React from 'react';
import ReactDOM from 'react-dom';
import MainForm from './MainForm';
import ApolloClient from 'apollo-boost';
import { gql } from "apollo-boost";


 //grapqlUrl
const client = new ApolloClient({
  uri: window.graphqlUrl
})



client.query({
  query: gql`
{
    getAnnualReport {
    lastName
    firstName
    employeeNumber
    annualEarnings
  }
}
` 
})
      .then(res =>  { 
        
       
        ReactDOM.render(<MainForm annualReportData={res.data.getAnnualReport} />, document.querySelector('#graphqlDemo'));

})





