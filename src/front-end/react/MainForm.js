import React from 'react';
import { Component } from 'react';
import EmployeeControl from './EmployeeControl';
import ApolloClient from 'apollo-boost';
import { gql } from "apollo-boost";
//https://blog.logrocket.com/the-new-react-lifecycle-methods-in-plain-approachable-language-61a2105859f3


//grapqlUrl
const client = new ApolloClient({
  uri: window.graphqlUrl
})


export default class MainForm extends Component {
  constructor(props) {
    super(props);
    this.emptyOffice = {employees: []};
    this.state = {offices: [], currentOffice: {officeCode: "-1",employees: []}};
  }
  componentDidMount() {

    client.query({
      query: gql`
{
   getOfficeExplorers {
      officeCode
      postalCode
      city
      country
      salesTotal
      phone
      employees {
        lastName
        firstName
        email
        employeeNumber
        salesTotal
        customers {
          customerName
          customerNumber
          salesTotal
        }
      }
    }
}
`
    })
          .then(res => {

          this.setState({offices:res.data.getOfficeExplorers })


          })





  }
  static getDerivedStateFromProps(props, state) {

    return null;
  }
  changeOffice(ev) {
    ev.preventDefault();

    let newOffice = this.state.offices.filter(o => {
      return o.officeCode === ev.target.value;

    });

    let setOffice = this.emptyOffice;
    if (newOffice && newOffice[0]) {
      setOffice = newOffice[0];
    }

    this.setState({currentOffice: setOffice});

  }
  showOfficeControl() {
    let reports = [<option key="0"    value="0">Select</option>];

    this.state.offices.forEach(r => {
      let des = r.city + " (" + r.country + ")";
      reports.push(<option value={r.officeCode} key={r.officeCode}>{des}
      </option>)

    })
    return reports;
  }
  render() {
    let selectShow = 'inherit';
    let imgShow = 'none';
    if (this.state.offices.length === 0) {
      selectShow = 'none';
      imgShow = 'inline';
    }


    let imgStyle = {marginLeft: "20px", display: imgShow};
    let selectStyle = {width: "150px", display: selectShow};



    return (
          <div>
              <div className="row">
                  <div className="column50Left">
                      <form className="form form-compressed">
                          <select style={selectStyle} defaultValue="0"   onChange={this.changeOffice.bind(this)}> {this.showOfficeControl()}</select>
                          <div style={imgStyle}> <img src="/webjars/jsapp/images/spiffygif_30x30.gif"></img></div>
                      </form>
                  </div>
          
              </div>
              <div className="row">
                  <EmployeeControl employees={this.state.currentOffice.employees} officeCode={this.state.currentOffice.officeCode} />
              </div>
          </div>
          )

  }
}