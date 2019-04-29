import React from 'react';
import { Component } from 'react';

export default class MainForm extends Component {
  componentDidMount() {

    console.log(this.props.annualReportData)

  }
  showReports() {
    let reports = [];

    this.props.annualReportData.forEach(r => {

      reports.push(<li key={r.employeeNumber}>  {r.lastName} -- Amt: {r.annualEarnings}</li>)

    })
    return reports;
  }
  render() {

    return (
          <div className="row">
              <ul> {this.showReports()}</ul>
           
          </div>

          )

  }
}