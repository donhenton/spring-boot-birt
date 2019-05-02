import React from 'react';
import { Component } from 'react';
import CustomerControl from './CustomerControl';


export default class EmployeeControl extends Component {
  constructor(props) {
    super();
    this.state = {currentEmployees: [], selectedEmployeeIndex: -1, officeCode: -1}
  }
  
  //https://hackernoon.com/replacing-componentwillreceiveprops-with-getderivedstatefromprops-c3956f7ce607
  static getDerivedStateFromProps(nextProps,prevState) {
    //apparently called each time when the employee radio is selected
    if (nextProps.officeCode !== prevState.officeCode) {
       return {currentEmployees: nextProps.employees, selectedEmployeeIndex: 0, officeCode: nextProps.officeCode};
    } else {
      return null;
    }
     
   
  }
  
  employeeChange(ev) {
    
    let newIndex = ev.currentTarget.value;
    //console.log(`employee change ${newIndex}`)
    this.setState({selectedEmployeeIndex: newIndex});
  }
  
  
  
  composeEmployees() {

    let emps = [];
    let me = this;
    let checked = '';
   // console.log(`selected emp ${this.state.selectedEmployeeIndex}`)
    this.state.currentEmployees.forEach((emp,idx) => {
      if (idx === parseInt(this.state.selectedEmployeeIndex)) {
        checked = 'checked';
      } else {
        checked = '';
      }
      emps.push(<tr key={emp.employeeNumber}><td>{emp.lastName}, {emp.firstName}</td><td><input type="radio" checked={checked} name="employeeChoice" onChange={me.employeeChange.bind(me)} value={idx}></input></td></tr>)
    });

    return emps;
  }
  
  getCurrentCustomers() {
    if ( this.state.currentEmployees[this.state.selectedEmployeeIndex] &&
          this.state.currentEmployees[this.state.selectedEmployeeIndex].customers) {
      return this.state.currentEmployees[this.state.selectedEmployeeIndex].customers;
    }
    return [];
  }
  
  render() {
   // console.log("render")
    return (
          <div className="row">
              <div className="column50Left">
                  <div className="column50Left">
                      <table className="table table-striped">
          
                          <tbody>
                              {this.composeEmployees()} 
                          </tbody>
          
                      </table>
                  </div>
              </div>
              <div className="column50Right">
             
              <CustomerControl customers={this.getCurrentCustomers()} />
            </div>
          </div>


          )

  }
}