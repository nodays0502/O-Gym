import { Modal, Button } from "antd";
import { useState } from "react";
import styled from "styled-components";

const StyledModal = styled(Modal)`
    
     .ant-modal-content {
            height: 100%;
    }

`;

const MainNavigation = (): JSX.Element => {

    const [isVisible, setIsVisible] = useState<boolean>(false);
    
    const clickMenuButton = () => {
        setIsVisible(!isVisible)
    }

    const clickModalClose = () => {
        setIsVisible(!isVisible)
    }

    return (
        <>
            <Button onClick={clickMenuButton}>
                show
            </Button>
            <StyledModal
                title="Text"
                style={{
                    position: "fixed",
                    top: "0px",
                    left: "0px",
                    bottom: "0px",
                    height: "100%"
                }}
                visible={isVisible}
                onOk={clickModalClose}
                onCancel={clickModalClose}
                footer={null}
            >
                 <p>some contents...</p>
                <p>some contents...</p>
                <p>some contents...</p>
             

            </StyledModal>
            
        </>
    );
}

export default MainNavigation;