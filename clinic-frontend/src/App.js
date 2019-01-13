import {ipcRenderer} from 'electron';
import React, { Component } from 'react';
import axios from './axios';
import './css/App.css';
import './css/Logo.css';
import Clinics from './components/clinics/Clinics';
import Clinic from './components/clinics/Clinic';
import NewClinic from './components/clinics/NewClinic';
import Welcome from './components/Welcome';
import Home from './components/Home';
import EditClinic from './components/clinics/EditClinic';

const constants = require('./constants/pages');

class App extends Component {

    constructor(props){
        super(props)
        this.state = {
            panel: constants.CLINICS,
            edit: null,
            clinics: []
        }
        this.changePanel = this.changePanel.bind(this);
        this.getClinicsHandler = this.getClinicsHandler.bind(this);
        this.deleteClinicHandler = this.deleteClinicHandler.bind(this);
        this.postClinicHandler = this.postClinicHandler.bind(this);
        this.editItemHandler = this.editItemHandler.bind(this);
        this.putClinicHandler = this.putClinicHandler.bind(this);
    }

    editItemHandler(itemData){
      this.setState({edit: itemData});
    }

    getClinicsHandler(){
      axios.get( '/clinics/all' )
          .then( response => {
              console.log(response)
              const clinics = response.data;
              const newClinics = clinics.map( (clinic, index) => {
                  return <Clinic 
                      key={index} 
                      data={clinic}
                      deleteClinicHandler={this.deleteClinicHandler}
                      editItemHandler={this.editItemHandler}
                      changePanel={this.changePanel}
                      />
              });
              this.setState({clinics: newClinics});
          })
          .catch( error => {
              console.log(error);
          });
    }

    deleteClinicHandler(id){
        axios.delete(`/clinics/${id}`)
            .then( response => {
                console.log(response);
                this.getClinicsHandler();
            })
            .catch( error => {
                console.log(error);
            });
    }

    putClinicHandler(data){
        axios.put(`/clinics/${data.id}`,data)
            .then( response => {
              console.log(response);
              this.getClinicsHandler();
            })
            .catch( error => {
                console.log(error);
            });
    }

    postClinicHandler(formdata){
        const data = {
            name: formdata.name,
            type: formdata.type,
            departments: [],
            localization: "Poznan"
        }
        axios.post('/clinics',data)
            .then( response => {
                console.log(response);
                this.getClinicsHandler();
            })
            .catch( error => {
                console.log(error);
            });
    }

    changePanel(panel){
      this.setState({panel: panel})
    }

    panelSwitch(){
      switch (this.state.panel){
          case constants.EDIT_CLINIC:
            return <EditClinic 
              changePanel={this.changePanel}
              data={this.state.edit}
              putClinicHandler={this.putClinicHandler}
            />
          case constants.NEW_CLINIC:
            return <NewClinic 
              changePanel={this.changePanel}
              postClinicHandler={this.postClinicHandler}
            />
          case constants.CLINICS:
            return <Clinics 
              clinics={this.state.clinics}
              changePanel={this.changePanel}
              getClinicsHandler={this.getClinicsHandler}
            />;
          case constants.WELCOME:
            return <Welcome changePanel={this.changePanel}/>;
          case constants.HOME:
            return <Home changePanel={this.changePanel}/>;
          default:
            return <div>Opps! You get lost!</div>
      }
    }

    render() {
        return (
          <div className={"App " + this.state.panel}>
              {this.panelSwitch()}
          </div>
        );
    }
}

export default App;

