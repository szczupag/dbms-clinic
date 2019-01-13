import React, { Component } from 'react';
import constants from '../constants/pages';

class Home extends Component {
    constructor(props){
        super(props)
    }

    render(){
        return(
            <div className="home">
                <div className="page-title">
                    <span>Home</span>
                </div>
                <div className="options">
                    <span className="subtitle">Choose panel</span>
                    <button className="default-btn" onClick={()=>this.props.changePanel(constants.CLINICS)}>Clinics</button>
                    <button className="default-btn">Departments</button>
                    <button className="default-btn">Localizations</button>
                    <button className="default-btn">Doctors</button>
                    <button className="default-btn">Specializations</button>

                </div>
            </div>
        )
    }
}

export default Home;