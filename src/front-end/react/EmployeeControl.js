import React from 'react';
import { Component } from 'react';


export default class EmployeeControl extends Component {
  constructor(props) {
    super();
    this.state = {currentEmployees: [], selectedEmployeeIndex:-1}
  }
  static getDerivedStateFromProps(props, state) {
    
    return {currentEmployees: props.employees,selectedEmployeeIndex:0};
  }
  composeEmployees() {

    let emps = [];

    this.state.currentEmployees.forEach(emp => {
      emps.push(<li key={emp.employeeNumber}>{emp.lastName}</li>)
    });

    return emps;
  }
  render() {
    return (
          <div className="row">
              <div className="column50Left">
                  <ul>{this.composeEmployees()}</ul>
              </div>
          </div>


          )

  }
}