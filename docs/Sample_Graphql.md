# SAMPLE GRAPHQL STATEMENTS


## Security Groups

```

  getSecurityGroups {
    groupName
    id
    applicationsNotInGroup {
      id
      applicationName
    }
    applications {
      id
      applicationName
    }
    usersNotInGroup {
      userId
      userName
      login
    }
    users {
      userId
      userName
      login
    }
  }
}

```

## Sales Reports

```
{
  getAnnualReport {
    lastName
    employeeNumber
    annualEarnings
  }
}

```

Other report

```
{
  getSalesReport(employeeId:1337) {
     lastName
     firstName
     totalSales
  }
   
}


```

## Mutation

```
mutation {
  createOffice (input: {
    officeCode: "8",
    city:"Blasting Cap",
    phone:"367-667-4478",
    addressLine1: "100 N South St",
    addressLine2: "Apt 100",
    postalCode: "45632-7890",
    territory: "bonzo"
    
  }) {officeCode,city}
}

```

## OfficeExplorers

```
query {
    getOfficeExplorers {
      officeCode
      postalCode
      phone
      employees {
        lastName
        firstName
        email
        employeeNumber
        customers {
          customerName
          customerNumber
          salesTotal
        }
      }
    }
}

```