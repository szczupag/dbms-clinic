import React, { Component }  from 'react';
import "./Panel.css";
import Doctor from "./Doctor/Doctor"

class Panel extends Component{
    typeSwitch(){
        switch (this.props.type){
            case "doctor":
                return <Doctor />;
            default:
                return null;
        }
    }

    render () {
        return (
            <div>
                <header className="App-header">
                    <p>{this.props.type}</p>
                </header>
                <div>
                    {this.typeSwitch()}
                </div>
            </div>
        );
    }
}

export default Panel;