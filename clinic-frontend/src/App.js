import React, { Component } from 'react';
import './App.css';
import Home from './components/Panels/Home/Home'
import Welcome from './components/Panels/Welcome/Welcome'
import Panel from './components/Panels/Panel/Panel'

class App extends Component {

    constructor(props){
        super(props)
        this.state = {
            panel: "hello"
        }
    }

    changePanel(arg){
        this.setState({panel: arg});
    }

    panelSwitch(){
      switch (this.state.panel){
          case "hello":
              return <Welcome
                  changePanel={this.changePanel.bind(this)}
              />;
          case "doctor":
              return <Panel type={this.state.panel}/>;
          default:
              return <Home
                  changePanel={this.changePanel.bind(this)}
              />;
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
