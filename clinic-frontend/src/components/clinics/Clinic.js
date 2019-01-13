import React, {Component} from 'react';
import constants from '../../constants/pages';
import Department from '../departments/Department';

class Clinic extends Component {
    constructor(props){
        super(props)
    }
    
    render(){
        return(
            <div className="clinic">
                <div className="item-header">
                    <p>{this.props.data.name}</p>
                </div>
                <div className="item-content">
                    <p className="label">type:</p>
                    <p>{this.props.data.type}</p>
                    <p className="label">departments:</p>
                    {
                        Object.keys(this.props.data.departments).length!=0 ?           
                            <span>{this.props.data.departments.map((department,index)=>{
                                return <Department key={index} data={department} />
                            })}</span> : <span className="empty">No departments in this clinic</span>
                    }
                    <p className="label">localization:</p>                
                    <p>{this.props.data.localization}</p>
                    
                </div>
                <div className="item-footer">
                    <div className="controls">
                        <button className="controls-btn edit" onClick={()=>{
                            this.props.editItemHandler(this.props.data);
                            this.props.changePanel(constants.EDIT_CLINIC);
                        }}>Edit</button>
                        <button className="controls-btn delete" onClick={()=>this.props.deleteClinicHandler(this.props.data.id)}>Delete</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default Clinic;